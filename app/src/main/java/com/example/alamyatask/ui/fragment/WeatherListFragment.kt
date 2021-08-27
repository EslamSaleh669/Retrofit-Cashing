package com.example.alamyatask.ui.fragment

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alamyatask.R
import com.example.alamyatask.ui.adapter.WeatherAdapter
import com.example.alamyatask.util.*
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.weatherlist.*
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class WeatherListFragment : Fragment() {

    @Inject
    @field:Named("main")
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private val autoDispose: AutoDispose = AutoDispose()
    var dialog: Dialog? = null

    private var CityName:String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoDispose.bindTo(this.lifecycle)
        (activity?.application as MyApplication).appComponent?.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weatherlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        dialog = activity?.launchLoadingDialog()

        arguments?.getString(Constants.CITY_NAME)?.let {
           CityName = it
        }

        toolbar_title.text="${CityName} ${resources.getString(R.string.weather)}"

        allweatheRecycler.adapter =
            WeatherAdapter(arrayListOf(), activity!!)
        allweatheRecycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        dialog!!.dismiss()

        getWeathwer()

    }


    fun getWeathwer(){
        autoDispose.add(
            viewModel.getWeather(CityName).observeOn(AndroidSchedulers.mainThread()).subscribe({
                if (it.list!!.isEmpty()) {
                    (allweatheRecycler.adapter as WeatherAdapter).addItems(arrayListOf())

                } else {
                    if (it.list.isNotEmpty()) {
                        (allweatheRecycler.adapter as WeatherAdapter).addItems(it.list)

                    }
                }


            }, {
                dialog!!.dismiss()
                noDataFound.visibility = View.VISIBLE
                allweatheRecycler.visibility = View.GONE
                activity?.makeToast(getString(R.string.citynotfound))
                Log.d("***error",it.toString())


            })
        )
    }

    fun hideKeyboard() {
        val imm = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view: View = activity!!.getCurrentFocus()!!
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}