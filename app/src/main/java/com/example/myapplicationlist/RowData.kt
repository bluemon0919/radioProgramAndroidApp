package com.example.myapplicationlist.model

public class RowData {
    private var id: String = ""
    private var title: String= ""
    private var weekday: String = ""
    private var deadline: String = ""
    private var url: String = ""

    fun setID(id: String) { this.id = id }
    fun setTitle(title: String) { this.title = title }
    fun setWeekday(weekday: String) { this.weekday = weekday }
    fun setDeadline(deadline: String) { this.deadline = deadline }
    fun setURL(url: String) { this.url = url }

    fun getID(): String {return this.id}
    fun getTitle(): String {return this.title}
    fun getWeekday(): String {return this.weekday}
    fun getDeadline(): String {return this.deadline}
    fun getURL(): String {return this.url}
}
