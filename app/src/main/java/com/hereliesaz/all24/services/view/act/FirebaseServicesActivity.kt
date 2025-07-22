package com.hereliesaz.all24.view.act

import com.hereliesaz.all24.contract.FirebaseServicesContract
import com.hereliesaz.all24.presenter.FirebaseServicesPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.hereliesaz.all24.R

/**
 * @Author Kotlin MVP Plugin
 * @Date 2025/07/22
 * @Description input description
 **/
class FirebaseServicesActivity : BaseMvpActivity<FirebaseServicesContract.IPresenter>() , FirebaseServicesContract.IView {

    override fun registerPresenter() = FirebaseServicesPresenter::class.java

    override fun getLayoutId() = R.layout.activity_firebaseservices

}
