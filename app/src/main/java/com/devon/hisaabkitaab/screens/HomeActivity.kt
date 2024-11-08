package com.devon.hisaabkitaab.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devon.hisaabkitaab.R
import com.devon.hisaabkitaab.databinding.ActivityHomeBinding
import com.devon.hisaabkitaab.screens.bank.BankBillsActivity
import com.devon.hisaabkitaab.screens.committee.CommitteActivity
import com.devon.hisaabkitaab.screens.electricity.ElectricityBillsActivity
import com.devon.hisaabkitaab.screens.utility.UtilityBillsActivity
import com.devon.hisaabkitaab.extensions.click
import com.devon.hisaabkitaab.extensions.gotoActivity
import com.devon.hisaabkitaab.extensions.setToolbar

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setToolbar(this, getString(R.string.home))

        initViews()

    }

    private fun initViews()
    {
        binding.btBankBills.click {
            gotoActivity(BankBillsActivity::class.java)
        }
        binding.btElectricityBills.click {
            gotoActivity(ElectricityBillsActivity::class.java)
        }
        binding.btUtilityBills.click {
            gotoActivity(UtilityBillsActivity::class.java)
        }
        binding.btBachelorsCommitee.click {
            gotoActivity(CommitteActivity::class.java)
        }
    }
}