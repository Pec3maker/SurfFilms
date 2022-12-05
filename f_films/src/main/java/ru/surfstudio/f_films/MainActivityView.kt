package ru.surfstudio.f_films

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.android.filmssurf.f_films.R

@AndroidEntryPoint
class MainActivityView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<FilmsFragmentView>(R.id.main_fragment_container)
                addToBackStack(null)
            }
        }
    }
}