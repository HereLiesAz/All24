package com.hereliesaz.all24.services.presenter

import com.hereliesaz.all24.services.contract.FirebaseServicesContract
import com.hereliesaz.all24.services.model.FirebaseServicesModel
import mvp.ljb.kt.presenter.BaseMvpPresenter

/**
 * @author Kotlin MVP Plugin
 * @date 2025/07/22
 * @description input description
 */
class FirebaseServicesPresenter : BaseMvpPresenter<FirebaseServicesContract.IView, FirebaseServicesContract.IModel>(), FirebaseServicesContract.IPresenter{

    override fun registerModel() = FirebaseServicesModel::class.java

}
