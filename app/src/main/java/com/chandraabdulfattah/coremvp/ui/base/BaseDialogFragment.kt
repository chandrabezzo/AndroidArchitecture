package com.chandraabdulfattah.coremvp.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import com.chandraabdulfattah.coremvp.di.component.ActivityComponent

/**
 * Created by bezzo on 21/12/17.
 */
open class BaseDialogFragment : DialogFragment(), BaseDialogFragmentView {

    var baseActivity: BaseActivity? = null
        private set
    //    private Unbinder mUnBinder;
    var dataReceived: Bundle? = null
    private var rootView: View? = null

    val activityComponent: ActivityComponent?
        get() = if (baseActivity != null) {
            baseActivity!!.activityComponent
        } else null

    protected open fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(setLayout(), container, false)
        //        setUnbinder(ButterKnife.bind(this, rootView));
        dataReceived = arguments
        onViewInitialized(savedInstanceState)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments != null) {
            dialog.setTitle(arguments!!.getString("title"))
        } else {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun getRootView(): View {
        return (activity as BaseActivity).rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val mActivity = context as BaseActivity?
            this.baseActivity = mActivity
            mActivity!!.onFragmentAttached()
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun dismissDialog(tag: String) {
        dismiss()
        baseActivity!!.onFragmentDetached(tag)
    }

    override fun setLayout(): Int {
        return 0
    }

    override fun showLoading() {
        baseActivity!!.hideLoading()
        if (baseActivity != null) {
            baseActivity!!.showLoading()
        }
    }

    override fun hideLoading() {
        if (baseActivity != null) {
            baseActivity!!.hideLoading()
        }
    }

    override fun openActivityOnTokenExpire() {
        // on token expire
    }

    override fun onError(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.onError(resId)
        }
    }

    override fun onError(message: String?) {
        if (baseActivity != null) {
            baseActivity!!.onError(message)
        }
    }

    override fun showMessage(message: String?) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(message)
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(resId)
        }
    }

    override fun isNetworkConnected(): Boolean {
        return if (baseActivity != null) {
            baseActivity!!.isNetworkConnected()
        } else false
    }

    override fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

    override fun showToast(message: String, duration: Int) {
        Toast.makeText(baseActivity, message, duration).show()
    }

    override fun goToActivity(c: Class<*>, bundle: Bundle?, isFinish: Boolean) {
        (activity as BaseActivity).goToActivity(c, bundle, isFinish)
    }

    override fun goToActivityClearAllStack(c: Class<*>, bundle: Bundle?) {
        (activity as BaseActivity).goToActivityClearAllStack(c, bundle)
    }

    override fun goToActivityForResult(c: Class<*>, bundle: Bundle?, result: Int) {
        (activity as BaseActivity).goToActivityForResult(c, bundle, result)
    }

    override fun finishActivityforResult(bundle: Bundle?, result: Int) {
        (activity as BaseActivity).finishActivityforResult(bundle, result)
    }

    override fun gotoFragment(contentReplace: Int, data: Bundle?, classFragment: Class<*>) {
        (activity as BaseActivity).gotoFragment(contentReplace, data, classFragment)
    }

    override fun showProgressDialog(message: String, cancelable: Boolean) {
        (activity as BaseActivity).showProgressDialog(message, cancelable)
    }

    override fun dismissProgressDialog() {
        (activity as BaseActivity).dismissProgressDialog()
    }

    //    public void setUnbinder(Unbinder unbinder){
    //        mUnBinder = unbinder;
    //    }

    override fun show(fragmentManager: FragmentManager, tag: String) {
        val ft = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            ft.remove(prevFragment)
        }
        ft.addToBackStack(null)
        show(ft, tag)
    }
}