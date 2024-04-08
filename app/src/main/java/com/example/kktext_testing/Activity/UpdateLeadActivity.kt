package com.example.kktext_testing.Activity

import android.Manifest
import android.app.*
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kktext_testing.Adapter.ConvertDealerProdListAdapter
import com.example.kktext_testing.Adapter.UpdateAllStatusAdapter
import com.example.kktext_testing.ApiHelper.ApiController
import com.example.kktext_testing.ApiHelper.ApiResponseListner
import com.example.kktext_testing.Model.*
import com.example.kktext_testing.R
import com.example.kktext_testing.Utills.*
import com.example.kktext_testing.databinding.ActivityUpdateLeadBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.stpl.antimatter.Utils.ApiContants
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*


class UpdateLeadActivity : AppCompatActivity(), ApiResponseListner,
    GoogleApiClient.OnConnectionFailedListener,
    ConnectivityListener.ConnectivityReceiverListener {
    private lateinit var binding: ActivityUpdateLeadBinding
    val cutomProdImgList: MutableList<CustProdImgBean> = ArrayList()
    val imgList: MutableList<File> = ArrayList()
    private lateinit var apiClient: ApiController
    private lateinit var btnAadharFront: ImageView
    private lateinit var tvImageCount: TextView
    private var calendar: Calendar? = null
    var myReceiver: ConnectivityListener? = null
    var fromDate = ""
    var leadID = ""
    var customerTypeData = ""
    var leadStatus = ""
    var leadPos = 0
    var dealerID = 0
    var projectType = "Completed"
    var activity: Activity = this
    val PERMISSION_CODE = 12345
    val CAMERA_PERMISSION_CODE1 = 123
    var SELECT_PICTURES1 = 1
    private var quoteListID: MutableList<Any?>?=null
    var file2: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_lead)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        myReceiver = ConnectivityListener()
        binding.igToolbar.tvTitle.text = "Update Lead"
        binding.igToolbar.ivMenu.setImageDrawable(resources.getDrawable(R.drawable.ic_back_black))
        binding.igToolbar.ivMenu.setOnClickListener { finish() }
        binding.igToolbar.ivLogout.visibility = View.GONE
        binding.igToolbar.switchDayStart.visibility = View.GONE
        leadID = intent.getStringExtra("leadID")!!
        if (intent.hasExtra("customerType")){
            customerTypeData = intent.getStringExtra("customerType")!!
        }else{
            customerTypeData=""
        }

       // leadStatus = intent.getStringExtra("leadStatus")!!
          requestPermission()
    //    typeMode()
        apiGetStatus()
       // apiGetDealer()
        calendar = Calendar.getInstance();
        val hour: Int = calendar!!.get(Calendar.HOUR_OF_DAY)
        val min: Int = calendar!!.get(Calendar.MINUTE)

        //   callCityListAdapter()

        binding.apply {
            btnLeadToEnquiry.setOnClickListener {
                startActivity(Intent(this@UpdateLeadActivity,InquiryActivity::class.java).putExtra("leadID",leadID))
            }

            btnRFQ.setOnClickListener {
                openDialog()
            }

            btnSend.setOnClickListener {
                apiSendLead()
            }
            btnDealerSubmit.setOnClickListener {
                apiSubmitLead()
            }

            ivView.setOnClickListener {  }

            editTime.setOnClickListener {
                val timePickerDialog = TimePickerDialog(
                    this@UpdateLeadActivity,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        editTime.setText(
                            "$hourOfDay:$minute"
                        )
                    },
                    hour,
                    min,
                    false
                )
                timePickerDialog.show()
            }

            editDate.setOnClickListener(View.OnClickListener {
                val c = Calendar.getInstance()
                val year = c[Calendar.YEAR]
                val month = c[Calendar.MONTH]
                val day = c[Calendar.DAY_OF_MONTH]
                val datePickerDialog = DatePickerDialog(
                    this@UpdateLeadActivity,
                    { view, year, monthOfYear, dayOfMonth ->
                        //  dob.setText(dateofnews);
                        val dateofnews = "${ year.toString()+ "-"+(monthOfYear + 1).toString()  + "-" + dayOfMonth.toString() }"

                        //   val dateofnews = (monthOfYear + 1).toString() + "/" + dayOfMonth + "/" + year
                        editDate.setText(dateofnews)
                    },
                    year, month, day
                )
                datePickerDialog.show()
            })

            btnSubmit.setOnClickListener {
                if (leadStatus.equals("") && leadStatus.isNullOrEmpty()) {
                    Toast.makeText(this@UpdateLeadActivity,"Please Select Status",Toast.LENGTH_SHORT).show()
                }else{
                    apiUpdateLead()
                }
                /*  if (leadPos==3||leadPos==4){

                    }else{
                        apiUpdateLead()
                    }*/
            }
        }
    }

    fun typeMode() {
        binding.radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if (checkedId == R.id.rbCompleted) {
                    projectType = "Completed"
                } else if (checkedId == R.id.rbPartial) {
                    projectType = "Partial"

                }
            }
        })
    }

    fun apiUpdateLead() {
        SalesApp.isAddAccessToken = true
        apiClient = ApiController(activity, this)
        val params = Utility.getParmMap()
        params["lead_id"] = leadID
        params["status"] = leadStatus
        params["remarks"] = binding.editRemark.text.toString()
        params["call_date"] = binding.editDate.text.toString()
        params["call_time"] = binding.editTime.text.toString()
        params["conversion"] = projectType
        params["gst"] = binding.editGST.text.toString()

        apiClient.progressView.showLoader()
        apiClient.getApiPostCall(ApiContants.UpdateLead, params)

    }

    fun apiSendLead() {
        SalesApp.isAddAccessToken = true
        apiClient = ApiController(activity, this)
        val params = Utility.getParmMap()
        params["lead_id"] = leadID
        apiClient.progressView.showLoader()
        apiClient.getApiPostCall(ApiContants.getSendLead, params)

    }

    fun apiConvertedLead() {
        SalesApp.isAddAccessToken = true
        apiClient = ApiController(activity, this)
        val params = Utility.getParmMap()
        params["lead_id"] = leadID
        apiClient.progressView.showLoader()
        apiClient.getApiPostCall(ApiContants.ConvertedDealer, params)
    }

    fun apiSubmitLead() {
        SalesApp.isAddAccessToken = true
        apiClient = ApiController(activity, this)
        val params = Utility.getParmMap()
        params["lead_id"] = leadID.toString()
        params["dealer_id"] = dealerID.toString()
        params["ids"] = Gson().toJson(quoteListID)
        params["status"] = leadStatus
        apiClient.progressView.showLoader()
        apiClient.getApiPostCall(ApiContants.UpdateDealerLead, params)
    }

    override fun success(tag: String?, jsonElement: JsonElement) {
        try {
            apiClient.progressView.hideLoader()
            if (tag == ApiContants.UpdateLead) {
                val updateLeadBean = apiClient.getConvertIntoModel<UpdateLeadBean>(
                    jsonElement.toString(),
                    UpdateLeadBean::class.java
                )

                Toast.makeText(this, updateLeadBean.msg, Toast.LENGTH_SHORT).show()
                finish()
            }
            if (tag == ApiContants.UpdateDealerLead) {
                val updateLeadBean = apiClient.getConvertIntoModel<UpdateLeadBean>(
                    jsonElement.toString(),
                    UpdateLeadBean::class.java
                )

                Toast.makeText(this, updateLeadBean.msg, Toast.LENGTH_SHORT).show()
                finish()
            }
            if (tag == ApiContants.getSendLead) {
                val updateLeadBean = apiClient.getConvertIntoModel<UpdateLeadBean>(
                    jsonElement.toString(),
                    UpdateLeadBean::class.java
                )

                Toast.makeText(this, updateLeadBean.msg, Toast.LENGTH_SHORT).show()

            }
            if (tag == ApiContants.getInsertRFQ) {
                val rfqBean = apiClient.getConvertIntoModel<RFQBean>(
                    jsonElement.toString(),
                    RFQBean::class.java
                )

                Toast.makeText(this, rfqBean.msg, Toast.LENGTH_SHORT).show()


            }
            if (tag == ApiContants.GetDealer) {
                val dealerBean = apiClient.getConvertIntoModel<DealerBean>(
                    jsonElement.toString(),
                    DealerBean::class.java
                )

                if (dealerBean.error == false) {
                    //   setDealer(dealerBean.data)

                    setDealer(dealerBean.data)
                    /* SalesApp.mepList.clear()
                     SalesApp.mepList.addAll(mepBean.data)*/
                }
            }
            if (tag == ApiContants.GetStatus) {
                val allStatusBean = apiClient.getConvertIntoModel<GetAllStatusBean>(
                    jsonElement.toString(),
                    GetAllStatusBean::class.java
                )
                //   Toast.makeText(this, allStatusBean.msg, Toast.LENGTH_SHORT).show()
                if (allStatusBean.error==false) {
                    handleRcStatus(allStatusBean.data)
                }

            }
            if (tag == ApiContants.ConvertedDealer) {
                val convertedDealerBean = apiClient.getConvertIntoModel<ConvertedDealerBean>(
                    jsonElement.toString(),
                    ConvertedDealerBean::class.java
                )
                //   Toast.makeText(this, allStatusBean.msg, Toast.LENGTH_SHORT).show()
                if (convertedDealerBean.error==false) {
                    if (customerTypeData.equals("customer")){
                        binding.inputDealer.visibility=View.VISIBLE
                        binding.rcProduct.visibility=View.VISIBLE
                        binding.btnSubmit.visibility=View.GONE
                        binding.btnDealerSubmit.visibility=View.VISIBLE
                        binding.btnSubmit.visibility=View.GONE
                    }else{
                        binding.inputDealer.visibility=View.GONE
                        binding.rcProduct.visibility=View.GONE
                        binding.btnSubmit.visibility=View.GONE
                        binding.btnDealerSubmit.visibility=View.GONE
                        binding.btnSubmit.visibility=View.VISIBLE
                    }


                    handleLeadProdList(convertedDealerBean.data.leadProducts)
                }

            }
        }catch (e:Exception){
            Log.d("error>>",e.localizedMessage)
        }

    }

    fun handleLeadProdList(
        leadProduct: List<ConvertedDealerBean.Data.LeadProduct>
    ) {
        binding.rcProduct.layoutManager = LinearLayoutManager(this)
        var mAdapter = ConvertDealerProdListAdapter(this, leadProduct, object :
            RvListClickListner {
            override fun clickPos(status: MutableList<Any?>?, id: Int) {
                Log.d("zxzxc",Gson().toJson(status))
                quoteListID=status
            }

        })
        binding.rcProduct.adapter = mAdapter
        // rvMyAcFiled.isNestedScrollingEnabled = false

    }

    fun apiGetStatus() {
        SalesApp.isAddAccessToken = true
        apiClient = ApiController(this, this)
        val params = Utility.getParmMap()
        apiClient.progressView.showLoader()
        apiClient.getApiPostCall(ApiContants.GetStatus, params)
        apiClient.getApiPostCall(ApiContants.GetDealer, params)

    }

    fun setDealer(data: List<DealerBean.Data>) {
        val state = arrayOfNulls<String>(data.size)
        for (i in data.indices) {
            state[i] = data.get(i).name+" ( "+data.get(i).phone+" ) "
        }

        binding.selectDealer.setAdapter(
            ArrayAdapter(
                this@UpdateLeadActivity,
                android.R.layout.simple_list_item_1, state
            )
        )

        binding.selectDealer.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->

            for (dealerBeanData in data) {
                val name=dealerBeanData.name+" ( "+dealerBeanData.phone+" ) "
                if (name.equals(
                        parent.getItemAtPosition(position).toString()
                    )
                ) {
                    dealerID = dealerBeanData.id
                    Log.d("StateID", "" + dealerBeanData.id)
                    binding.selectDealer.setText(parent.getItemAtPosition(position).toString())
               //     apiDealerProduct(dealerBeanData.id)
                    Toast.makeText(
                        applicationContext,
                        binding.selectDealer.getText().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


            setDealer(data)
        })
    }

    override fun failure(tag: String?, errorMessage: String) {
        apiClient.progressView.hideLoader()

        Utility.showSnackBar(activity, errorMessage)
        Log.d("error",errorMessage)

    }

    fun handleRcStatus(data: List<GetAllStatusBean.Data>) {
        binding.rcStatus.layoutManager = GridLayoutManager(this,3)
        var mAdapter = UpdateAllStatusAdapter(this, data, object :
            RvStatusClickListner {
            override fun clickPos(status: String, pos: Int) {
                if (status.equals("CALL SCHEDULED")||status.equals("VISIT SCHEDULED"))
                    binding.llDateTimeSection.visibility=View.VISIBLE
                else if (status.equals("CONVERTED")){

                    apiConvertedLead()
                }else{
                    binding.llDateTimeSection.visibility=View.GONE
                    binding.btnSubmit.visibility=View.VISIBLE
                    binding.btnDealerSubmit.visibility=View.GONE
                    binding.inputDealer.visibility=View.GONE
                    binding.rcProduct.visibility=View.GONE
                }

                leadStatus=status
                leadPos=pos

            }
        })
        binding.rcStatus.adapter = mAdapter
        // rvMyAcFiled.isNestedScrollingEnabled = false

    }

    fun handleConvertedLead(data: List<GetAllStatusBean.Data>) {
        binding.rcStatus.layoutManager = GridLayoutManager(this,3)
        var mAdapter = UpdateAllStatusAdapter(this, data, object :
            RvStatusClickListner {
            override fun clickPos(status: String, pos: Int) {


                leadStatus=status
                leadPos=pos

            }
        })
        binding.rcStatus.adapter = mAdapter
        // rvMyAcFiled.isNestedScrollingEnabled = false

    }

    fun ClickPicCamera(CAMERA_PERMISSION_CODE: Int) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_PERMISSION_CODE)
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES),
            PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==PERMISSION_CODE){
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission is Granted",Toast.LENGTH_SHORT).show()

            }
            else{
                requestPermission()
            }
        }
    }

    private fun uploadImage(SELECT_PICTURES: Int) {
        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose Pictures")
                , SELECT_PICTURES
            )
        }
        else { // For latest versions API LEVEL 19+
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_PICTURES);
        }
    }

    fun openCameraDialog(SELECT_PICTURES: Int,CAMERA_PERMISSION_CODE: Int) {
        val dialog: Dialog = GeneralUtilities.openBootmSheetDailog(R.layout.dialog_camera, R.style.AppBottomSheetDialogTheme,
            this
        )
        val ivClose = dialog.findViewById<ImageView>(R.id.ivClose)
        val llInternalPhoto = dialog.findViewById<View>(R.id.llInternalPhoto) as LinearLayout
        val llClickPhoto = dialog.findViewById<View>(R.id.llClickPhoto) as LinearLayout

        llInternalPhoto.setOnClickListener { dialog.dismiss()
            requestPermission()
            uploadImage(SELECT_PICTURES)
        }

        llClickPhoto.setOnClickListener {
            dialog.dismiss()
            requestPermission()
            ClickPicCamera(CAMERA_PERMISSION_CODE)
        }
        ivClose.setOnClickListener { dialog.dismiss() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURES1){
            if (data?.getClipData() != null) { // if multiple images are selected
                var count = data.clipData?.itemCount
                tvImageCount.visibility=View.VISIBLE
                tvImageCount.text="$count Images"
                Log.d("wewwe", "$count")
                for (i in 0..count!! - 1) {
                    var imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
                    val picturePath: String = GeneralUtilities.getPath(
                        applicationContext, imageUri
                    )
                    file2 = File(picturePath)
                    //val custImg = CustProdImgBean(file2)
                    imgList.add(file2!!)
                 //   Log.d("MultiPicturePath", picturePath)
                    Log.d("MultiPicturePath", Gson().toJson(cutomProdImgList))
                    //     iv_image.setImageURI(imageUri) Here you can assign your Image URI to the ImageViews
                }

            } else if (data?.getData() != null) {   // if single image is selected
                tvImageCount.visibility=View.GONE
                var imageUri: Uri = data.data!!
                val picturePath: String = GeneralUtilities.getPath(
                    applicationContext, imageUri)
                file2 = File(picturePath)
                val myBitmap = BitmapFactory.decodeFile(file2!!.absolutePath)
                btnAadharFront.setImageBitmap(myBitmap)
                imgList.add(file2!!)
              /*  val custImg = CustProdImgBean(file2)
                val arrlis:ArrayList<File>
                cutomProdImgList.add(custImg)*/
                Log.d("SinglePicturePath", picturePath)
                //   iv_image.setImageURI(imageUri) Here you can assign the picked image uri to your imageview
            }

        }
         if (requestCode == CAMERA_PERMISSION_CODE1) {
            try {
                Toast.makeText(this@UpdateLeadActivity,"sdfsd",Toast.LENGTH_SHORT).show()

                val imageBitmap = data?.extras?.get("data") as Bitmap
                btnAadharFront.setImageBitmap(imageBitmap)
                val tempUri =GeneralUtilities.getImageUri(applicationContext, imageBitmap)
                file2= File(GeneralUtilities.getRealPathFromURII(this,tempUri))
                imgList.add(file2!!)
                Log.e("Path", file2.toString())

                //Toast.makeText(getContext(), ""+picturePath, Toast.LENGTH_SHORT).show();
            } catch (e: java.lang.Exception) {
                Log.e("Path Error", e.toString())
                Toast.makeText(applicationContext, "" + e, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openDialog(){
        val builder = AlertDialog.Builder(this,R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.dialog_multiimage,null)
        val  submit = view.findViewById<Button>(R.id.dialogDismiss_button)
        val  editRemark = view.findViewById<TextInputEditText>(R.id.editRemark)
          btnAadharFront = view.findViewById<ImageView>(R.id.btnAadharFront)
        val  ivClose = view.findViewById<ImageView>(R.id.ivClose)
          tvImageCount = view.findViewById<TextView>(R.id.tvImageCount)
        builder.setView(view)
        ivClose.setOnClickListener {
            builder.dismiss()
        }
        submit.setOnClickListener {
            builder.dismiss()
            apiRFQ(editRemark.text.toString())
        }
        btnAadharFront.setOnClickListener {
          //  uploadImage(SELECT_PICTURES1)
            openCameraDialog(SELECT_PICTURES1,CAMERA_PERMISSION_CODE1)
        }

        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }

    fun apiRFQ(remark: String) {
        SalesApp.isAddAccessToken = true
        val builder = MultipartBody.Builder()
        builder.setType(MultipartBody.FORM)
        builder.addFormDataPart("lead_id", leadID)
        builder.addFormDataPart("remarks", remark)
 /*       builder.addFormDataPart("files[]", file2?.name,
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(),Gson().toJson(imgList)))*/

       /* builder.addFormDataPart("files[]", "img[" + 0 + "]"+System.currentTimeMillis(),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), Gson().toJson(imgList)))*/

         for (i in 0 until imgList.size) {
          builder.addFormDataPart("files[]", imgList.get(i).name,
              RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imgList.get(i)))
      }

       /* if (file != null) {
            builder.addFormDataPart("image", file?.name,
                RequestBody.create(MediaType.parse("multipart/form-data"), file))
        }*/

    /*    val mediaType = "text/plain".toMediaTypeOrNull()
        val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("lead_id", "25")
            .addFormDataPart(
                "files[]", "/C:/Users/LENOVO/Downloads/lead-ex-2.png",
                create(
                    "application/octet-stream".toMediaTypeOrNull(),
                    File("/C:/Users/LENOVO/Downloads/lead-ex-2.png")
                )
            )
            .addFormDataPart(
                "files[]", "/C:/Users/LENOVO/Downloads/lead-ex.png",
                create(
                    "application/octet-stream".toMediaTypeOrNull(),
                    File("/C:/Users/LENOVO/Downloads/lead-ex.png")
                )
            )
            .addFormDataPart("remarks", "asasdlfk")
            .build()*/







        Log.d("requestParms", Gson().toJson(builder))
        apiClient.progressView.showLoader()
        apiClient.makeCallMultipart(ApiContants.getInsertRFQ, builder.build())

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        GeneralUtilities.unregisterBroadCastReceiver(this, myReceiver)
    }

    override fun onResume() {
        GeneralUtilities.registerBroadCastReceiver(this, myReceiver)
        SalesApp.setConnectivityListener(this)
        super.onResume()
    }
    override fun onDestroy() {
        super.onDestroy()
        // Start the LocationService when the app is closed
        startService(Intent(this, LocationService::class.java))
    }
    override fun onNetworkConnectionChange(isconnected: Boolean) {
        ApiContants.isconnectedtonetwork = isconnected
        GeneralUtilities.internetConnectivityAction(this, isconnected)
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {}
}
