package com.hereliesaz.all24.services.view.act

import com.hereliesaz.all24.R
import com.hereliesaz.all24.services.contract.FirebaseServicesContract
import com.hereliesaz.all24.services.presenter.FirebaseServicesPresenter
import mvp.ljb.kt.act.BaseMvpActivity

/**
 * @author Kotlin MVP Plugin
 * @date 2025/07/22
 * @description input description
 */
class FirebaseServicesActivity : BaseMvpActivity<FirebaseServicesContract.IPresenter>() , FirebaseServicesContract.IView {

    override fun registerPresenter() = FirebaseServicesPresenter::class.java

    override fun getLayoutId() = R.layout.activity_firebaseservices

}
