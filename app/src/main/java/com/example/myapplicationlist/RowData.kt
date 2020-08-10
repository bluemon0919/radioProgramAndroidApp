package com.example.myapplicationlist.model

public class RowData {
    private var title: String= ""
    private var detail: String = ""

    fun setTitle(title: String) { this.title = title }
    fun setDetail(detail: String) {this.detail = detail}

    fun getTitle(): String {return this.title}
    fun getDetail(): String {return this.detail}
}
