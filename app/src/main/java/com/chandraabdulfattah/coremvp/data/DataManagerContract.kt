package com.chandraabdulfattah.coremvp.data

import com.chandraabdulfattah.coremvp.data.local.LocalStorageHelperContract
import com.chandraabdulfattah.coremvp.data.network.ApiHelperContract
import com.chandraabdulfattah.coremvp.data.session.SessionHelperContract

/**
 * Created by bezzo on 26/09/17.
 */

interface DataManagerContract : LocalStorageHelperContract, ApiHelperContract, SessionHelperContract {

}
