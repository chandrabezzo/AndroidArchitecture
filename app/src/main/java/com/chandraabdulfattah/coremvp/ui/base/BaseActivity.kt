package com.chandraabdulfattah.coremvp.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatTextView
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder
import com.chandraabdulfattah.coremvp.MvpApp
import com.chandraabdulfattah.coremvp.R
import com.chandraabdulfattah.coremvp.di.component.ActivityComponent
import com.chandraabdulfattah.coremvp.di.component.DaggerActivityComponent
import com.chandraabdulfattah.coremvp.di.module.ActivityModule
import com.chandraabdulfattah.coremvp.util.CommonUtils
import com.chandraabdulfattah.coremvp.util.NetworkUtils
import kotlinx.android.synthetic.main.default_toolbar.*

/**
 * Created by bezzo on 26/09/17.
 * Uncomment code below Butter Knife if you use ButterKnife
 */

open class BaseActivity(var unbinder: Unbinder? = null) : AppCompatActivity(), BaseActivityView, BaseFragment.Callback {

    lateinit var mProgressDialog: ProgressDialog
    lateinit var activityComponent: ActivityComponent
    lateinit var mActionBar: ActionBar
    lateinit var dataReceived: Bundle
    lateinit var mContext: Context
    //Butter Knife
//    var mUnbinder : Unbinder? = null

    val rootView: View
        get() = findViewById(android.R.id.content)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as MvpApp).component)
                .build()
        setContentView(setLayout())
        mProgressDialog = ProgressDialog(this)
        mContext = this
        dataReceived = intent.extras

        setSupportActionBar(toolbar)

        mActionBar = supportActionBar!!

        if (toolbar != null){
            toolbar.setNavigationOnClickListener(View.OnClickListener { view: View? ->
                onNavigationClick(view!!)
            })
        }

        if (intent != null) {
            dataReceived = intent.extras
        }

        onInitializedView(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        // Butter Knife
//        setButterknifeUnbinder(ButterKnife.bind(this))
    }

    // Butter Knife
//    fun setButterknifeUnbinder(unbinder : Unbinder){
//        mUnbinder = unbinder
//    }

    override fun onDestroy() {
        // Butter Knife
//        mUnbinder?.unbind()
        super.onDestroy()
    }

    protected open fun onInitializedView(savedInstanceState: Bundle?) {

    }

    fun onNavigationClick(view: View) {
        onBackPressed()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(TAG: String) {

    }

    override fun setLayout(): Int {
        return 0
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    override fun openActivityOnTokenExpire() {
//        goToActivityClearAllStack(LoginActivity::class.java, null)
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message, Snackbar.LENGTH_SHORT)
        } else {
            showSnackBar(getString(R.string.some_error), Snackbar.LENGTH_SHORT)
        }
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            showToast(message, Toast.LENGTH_SHORT)
        } else {
            showToast(getString(R.string.some_error), Toast.LENGTH_SHORT)
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }

    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromInputMethod(view.windowToken, 0)
        }
    }

    override fun showToast(message: String, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    override fun goToActivity(c: Class<*>, bundle: Bundle?, isFinish: Boolean) {
        val i = Intent(this, c)
        if (bundle != null) {
            i.putExtras(bundle)
        }
        startActivity(i)
        if (isFinish) {
            finish()
        }
    }

    override fun goToActivityClearAllStack(c: Class<*>, bundle: Bundle?) {
        val i = Intent(this, c)
        if (bundle != null) {
            i.putExtras(bundle)
        }
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(i)
        finish()
    }

    override fun goToActivityForResult(c: Class<*>, bundle: Bundle?, result: Int) {
        val i = Intent(this, c)
        if (bundle != null) {
            i.putExtras(bundle)
        }
        startActivityForResult(i, result)
    }

    override fun finishActivityforResult(bundle: Bundle?, result: Int) {
        val i = Intent()
        if (bundle != null) {
            i.putExtras(bundle)
        }
        setResult(result, i)
        finish()
    }

    override fun gotoFragment(contentReplace: Int, data: Bundle?, classFragment: Class<*>) {
        var fragment: Fragment? = null

        try {
            fragment = classFragment.newInstance() as Fragment
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        val transaction = supportFragmentManager
                .beginTransaction()

        if (data != null) {
            fragment!!.arguments = data
        }

        transaction.replace(contentReplace, fragment)

        transaction.commit()
    }

    override fun showProgressDialog(message: String, cancelable: Boolean) {
        mProgressDialog!!.setMessage(message)
        mProgressDialog!!.setCancelable(cancelable)
        if (!mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }

    override fun dismissProgressDialog() {
        if (mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    override fun gotoDialog(dialogClass: Class<*>, data: Bundle?) {
        val fm = supportFragmentManager
        var fragment: DialogFragment? = null

        try {
            fragment = dialogClass.newInstance() as DialogFragment
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        if (data != null) {
            fragment!!.arguments = data
        }

        fragment!!.show(fm, "TAG")
    }

    override fun getContext(): Context? {
        return mContext
    }

    override fun displayHome() {
        if (mActionBar != null) {
            mActionBar!!.setHomeButtonEnabled(true)
            mActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            mActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun setActionBarTitle(title: String) {
        if (mActionBar != null) {
            mActionBar!!.title = title
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun showSnackBar(message: String, duration: Int) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, duration)
        val subView = snackbar.view
        val textView = subView.findViewById<View>(android.support.design
                .R.id.snackbar_text) as AppCompatTextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }
}
