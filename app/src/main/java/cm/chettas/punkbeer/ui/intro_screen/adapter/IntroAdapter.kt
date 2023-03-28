package cm.chettas.punkbeer.ui.intro_screen.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import cm.chettas.punkbeer.ui.intro_screen.fragment.FirstIntroFragment
import cm.chettas.punkbeer.ui.intro_screen.fragment.SecondIntroFragment
import cm.chettas.punkbeer.ui.intro_screen.fragment.ThirdIntroFragment

class IntroAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList = listOf(
        FirstIntroFragment(),
        SecondIntroFragment(),
        ThirdIntroFragment()
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}