package com.example.switchexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.rxjava3.subjects.PublishSubject

class MainActivity : AppCompatActivity() {

    private lateinit var searchText: TextInputLayout
    private var searchSwitcher:PublishSubject<String> = PublishSubject.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchSwitcher
            .switchMap {
                return@switchMap PublishSubject.just(it)
            }
            .doOnNext(System.out::println)
            .subscribe()

        searchText = findViewById(R.id.searchText)

        searchText.editText?.addTextChangedListener {
            searchSwitcher.onNext(it.toString())
        }
    }
}