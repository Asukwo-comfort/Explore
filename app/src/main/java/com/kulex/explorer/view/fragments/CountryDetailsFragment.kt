package com.kulex.explorer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kulex.explorer.R
import com.kulex.explorer.databinding.FragmentCountryDetailsBinding
import com.kulex.explorer.models.CountryItemItem
import com.kulex.explorer.util.LoadingState
import com.kulex.explorer.viewmodel.CountryDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailsFragment : Fragment(R.layout.fragment_country_details) {
    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CountryDetailsViewModel by viewModels()

    private val args: CountryDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDetail.setNavigationOnClickListener { requireActivity().onBackPressed() }

        fetchCountryDetails()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun fetchCountryDetails() {

        viewModel.fetchCountryDetails(args.countryName)
        viewModel.countryDetailsObservable.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }

                is LoadingState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }

                is LoadingState.Success -> {
                    state.data.let {
                        val country = it?.get(0)
                        bindUI(country!!)
                    }
                }

            }

        }
    }

    private fun bindUI(country: CountryItemItem) {


        binding.countryNameTv.text = country.name.official
        binding.populationTv.text = country.population.toString()
        binding.regionTv.text = country.region

        binding.imageview.load(country.flags.png) {
            transformations(RoundedCornersTransformation(16f))
        }


        binding.capitalCityTv.text= country.capital?.get(0).toString()


        binding.drivingSideTv.text = country.car.side.toString()
        binding.areaTv.text = country.area.toString().plus(" KM")
        if (country.independent == true){
            binding.independenceTv.text = "Independent"

        }

        if (country.independent ==false){
            binding.independenceTv.text = "Not Independent"
        }

        binding.timezoneTv.text = country.timezones.toString()


        if (!country.languages?.afr.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.afr}".plus(", "))
        }
        if (!country.languages?.amh.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.amh}".plus(", "))
        }
        if (!country.languages?.ara.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ara}".plus(", "))
        }
        if (!country.languages?.arc.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.arc}".plus(", "))
        }
        if (!country.languages?.aym.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.aym}".plus(", "))
        }
        if (!country.languages?.aze.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.aze}".plus(", "))
        }
        if (!country.languages?.bar.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.bar}".plus(", "))
        }
        if (!country.languages?.bel.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.bel}".plus(", "))
        }
        if (!country.languages?.ben.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ben}".plus(", "))
        }
        if (!country.languages?.ber.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ber}".plus(", "))
        }
        if (!country.languages?.bis.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.bis}".plus(", "))
        }
        if (!country.languages?.bjz.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.bjz}".plus(", "))
        }
        if (!country.languages?.bos.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.bos}".plus(", "))
        }
        if (!country.languages?.bul.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.bul}".plus(", "))
        }
        if (!country.languages?.bwg.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.bwg}".plus(", "))
        }
        if (!country.languages?.cal.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.cal}".plus(", "))
        }
        if (!country.languages?.cat.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.cat}".plus(", "))
        }
        if (!country.languages?.ces.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ces}".plus(", "))
        }
        if (!country.languages?.cha.isNullOrEmpty()) {

            binding.languagesTv.append("${country.languages?.cha}".plus(", "))

        }
        if (!country.languages?.ckb.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ckb}".plus(", "))
        }
        if (!country.languages?.cnr.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.cnr}".plus(", "))
        }
        if (!country.languages?.crs.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.crs}".plus(", "))
        }
        if (!country.languages?.dan.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.dan}".plus(", "))
        }
        if (!country.languages?.deu.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.deu}".plus(", "))
        }
        if (!country.languages?.div.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.div}".plus(", "))
        }
        if (!country.languages?.dzo.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.dzo}".plus(", "))
        }
        if (!country.languages?.ell.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ell}".plus(", "))
        }
        if (!country.languages?.eng.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.eng}".plus(", "))
        }
        if (!country.languages?.est.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.est}".plus(", "))
        }
        if (!country.languages?.fao.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.fao}".plus(", "))
        }
        if (!country.languages?.fas.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.fas}".plus(", "))
        }
        if (!country.languages?.fij.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.fij}".plus(", "))
        }
        if (!country.languages?.fil.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.fil}".plus(", "))
        }
        if (!country.languages?.fin.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.fin}".plus(", "))
        }
        if (!country.languages?.fra.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.fra}".plus(", "))
        }
        if (!country.languages?.gil.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.gil}".plus(", "))
        }
        if (!country.languages?.gle.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.gle}".plus(", "))
        }
        if (!country.languages?.glv.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.glv}".plus(", "))
        }
        if (!country.languages?.grn.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.grn}".plus(", "))
        }
        if (!country.languages?.gsw.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.gsw}".plus(", "))
        }
        if (!country.languages?.hat.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.hat}".plus(", "))
        }
        if (!country.languages?.heb.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.heb}".plus(", "))
        }
        if (!country.languages?.her.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.her}".plus(", "))
        }
        if (!country.languages?.hgm.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.hgm}".plus(", "))
        }
        if (!country.languages?.hif.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.hif}".plus(", "))
        }
        if (!country.languages?.hin.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.hin}".plus(", "))
        }
        if (!country.languages?.hmo.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.hmo}".plus(", "))
        }
        if (!country.languages?.hrv.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.hrv}".plus(", "))
        }
        if (!country.languages?.hun.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.hun}".plus(", "))
        }
        if (!country.languages?.hye.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.hye}".plus(", "))
        }
        if (!country.languages?.ind.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ind}".plus(", "))
        }
        if (!country.languages?.isl.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.isl}".plus(", "))
        }
        if (!country.languages?.ita.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ita}".plus(", "))
        }
        if (!country.languages?.jam.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.jam}".plus(", "))
        }
        if (!country.languages?.jpn.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.jpn}".plus(", "))
        }
        if (!country.languages?.kal.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kal}".plus(", "))
        }
        if (!country.languages?.kat.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kat}".plus(", "))
        }
        if (!country.languages?.kaz.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kaz}".plus(", "))
        }
        if (!country.languages?.kck.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kck}".plus(", "))
        }
        if (!country.languages?.khi.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.khi}".plus(", "))
        }
        if (!country.languages?.khm.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.khm}".plus(", "))
        }
        if (!country.languages?.kin.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kin}".plus(", "))
        }
        if (!country.languages?.kir.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kir}".plus(", "))
        }
        if (!country.languages?.kon.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kon}".plus(", "))
        }
        if (!country.languages?.kor.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kor}".plus(", "))
        }
        if (!country.languages?.kwn.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.kwn}".plus(", "))
        }
        if (!country.languages?.lao.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.lao}".plus(", "))
        }
        if (!country.languages?.lat.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.lat}".plus(", "))
        }
        if (!country.languages?.lav.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.lav}".plus(", "))
        }
        if (!country.languages?.lin.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.lin}".plus(", "))
        }
        if (!country.languages?.lit.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.lit}".plus(", "))
        }
        if (!country.languages?.loz.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.loz}".plus(", "))
        }
        if (!country.languages?.ltz.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ltz}".plus(", "))
        }
        if (!country.languages?.lua.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.lua}".plus(", "))
        }
        if (!country.languages?.mah.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mah}".plus(", "))
        }
        if (!country.languages?.mey.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mey}".plus(", "))
        }
        if (!country.languages?.mfe.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mfe}".plus(", "))
        }
        if (!country.languages?.mkd.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mkd}".plus(", "))
        }
        if (!country.languages?.mlg.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mlg}".plus(", "))
        }
        if (!country.languages?.mlt.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mlt}".plus(", "))
        }
        if (!country.languages?.mon.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mon}".plus(", "))
        }
        if (!country.languages?.mri.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mri}".plus(", "))
        }
        if (!country.languages?.msa.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.msa}".plus(", "))
        }
        if (!country.languages?.mya.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.mya}".plus(", "))
        }
        if (!country.languages?.nau.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nau}".plus(", "))
        }
        if (!country.languages?.nbl.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nbl}".plus(", "))
        }
        if (!country.languages?.ndc.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ndc}".plus(", "))
        }
        if (!country.languages?.nde.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nde}".plus(", "))
        }
        if (!country.languages?.ndo.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ndo}".plus(", "))
        }
        if (!country.languages?.nep.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nep}".plus(", "))
        }
        if (!country.languages?.nfr.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nfr}".plus(", "))
        }
        if (!country.languages?.niu.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.niu}".plus(", "))
        }
        if (!country.languages?.nld.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nld}".plus(", "))
        }
        if (!country.languages?.nno.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nno}".plus(", "))
        }
        if (!country.languages?.nob.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nob}".plus(", "))
        }
        if (!country.languages?.nor.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nor}".plus(", "))
        }
        if (!country.languages?.nrf.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nrf}".plus(", "))
        }
        if (!country.languages?.nso.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nso}".plus(", "))
        }
        if (!country.languages?.nya.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.nya}".plus(", "))
        }
        if (!country.languages?.nzs.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ltz}".plus(", "))
        }
        if (!country.languages?.pap.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.pap}".plus(", "))
        }
        if (!country.languages?.pau.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.pau}".plus(", "))
        }
        if (!country.languages?.pih.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.pih}".plus(", "))
        }
        if (!country.languages?.pol.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.pol}".plus(", "))
        }
        if (!country.languages?.por.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.por}".plus(", "))
        }
        if (!country.languages?.pov.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.pov}".plus(", "))
        }
        if (!country.languages?.prs.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.prs}".plus(", "))
        }
        if (!country.languages?.pus.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.pus}".plus(", "))
        }
        if (!country.languages?.que.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.que}".plus(", "))
        }
        if (!country.languages?.rar.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.rar}".plus(", "))
        }
        if (!country.languages?.roh.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.roh}".plus(", "))
        }
        if (!country.languages?.ron.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ron}".plus(", "))
        }
        if (!country.languages?.run.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.run}".plus(", "))
        }
        if (!country.languages?.rus.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.rus}".plus(", "))
        }
        if (!country.languages?.sag.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.sag}".plus(", "))
        }
        if (!country.languages?.sin.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.sin}".plus(", "))
        }
        if (!country.languages?.slk.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.slk}".plus(", "))
        }
        if (!country.languages?.smi.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.smi}".plus(", "))
        }
        if (!country.languages?.smo.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.smo}".plus(", "))
        }
        if (!country.languages?.sna.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.sna}".plus(", "))
        }
        if (!country.languages?.som.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.som}".plus(", "))
        }
        if (!country.languages?.sot.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.sot}".plus(", "))
        }
        if (!country.languages?.spa.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.spa}".plus(", "))
        }

        if (!country.languages?.sqi.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.sqi}".plus(", "))
        }

        if (!country.languages?.srp.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.srp}".plus(", "))
        }

        if (!country.languages?.ssw.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ssw}".plus(", "))
        }

        if (!country.languages?.swa.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.swa}".plus(", "))
        }
        if (!country.languages?.swe.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.swe}".plus(", "))
        }
        if (!country.languages?.tam.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tam}".plus(", "))
        }
        if (!country.languages?.tet.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tet}".plus(", "))
        }
        if (!country.languages?.tgk.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tgk}".plus(", "))
        }
        if (!country.languages?.tha.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tha}".plus(", "))
        }
        if (!country.languages?.tir.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tir}".plus(", "))
        }

        if (!country.languages?.toi.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.toi}".plus(", "))
        }

        if (!country.languages?.ton.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ton}".plus(", "))
        }

        if (!country.languages?.tpi.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tpi}".plus(", "))
        }
        if (!country.languages?.tsn.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tsn}".plus(", "))
        }
        if (!country.languages?.tso.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tso}".plus(", "))
        }
        if (!country.languages?.tuk.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tuk}".plus(", "))
        }


        if (!country.languages?.tur.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tur}".plus(", "))
        }
        if (!country.languages?.tvl.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.tvl}".plus(", "))
        }
        if (!country.languages?.ukr.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ukr}".plus(", "))
        }
        if (!country.languages?.urd.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.urd}".plus(", "))
        }

        if (!country.languages?.uzb.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.uzb}".plus(", "))
        }
        if (!country.languages?.ven.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.ven}".plus(", "))
        }
        if (!country.languages?.vie.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.vie}".plus(", "))
        }
        if (!country.languages?.xho.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.xho}".plus(", "))
        }
        if (!country.languages?.zdj.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.zdj}".plus(", "))
        }
        if (!country.languages?.zho.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.zho}".plus(", "))
        }
        if (!country.languages?.zib.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.zib}".plus(", "))
        }
        if (!country.languages?.zul.isNullOrEmpty()) {
            binding.languagesTv.append("${country.languages?.zul}".plus(", "))
        }

    }


}