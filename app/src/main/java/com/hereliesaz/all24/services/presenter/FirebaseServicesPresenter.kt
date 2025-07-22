package com.hereliesaz.all24.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.hereliesaz.all24.contract.FirebaseServicesContract
import com.hereliesaz.all24.model.FirebaseServicesModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2025/07/22
 * @Description input description
 **/
class FirebaseServicesPresenter : BaseMvpPresenter<FirebaseServicesContract.IView, FirebaseServicesContract.IModel>(), FirebaseServicesContract.IPresenter{

    override fun registerModel() = FirebaseServicesModel::class.java

}
