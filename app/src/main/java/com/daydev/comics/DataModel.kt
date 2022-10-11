package com.daydev.comics

class DataModel {
    var title: String?=null
    var desc: String?=null
    var photos: String?=null
    var files: String?=null
    var key:String?=null
    var photo:String?=null

    constructor(title: String?, desc: String?, photos: String?, files: String?, key: String?, photo: String?) {
        this.title = title
        this.desc = desc
        this.photos = photos
        this.files = files
        this.key = key
        this.photo = photo
    }
    constructor()

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("desc", desc!!)
        result.put("photos", photos!!)
        result.put("key", key!!)
        result.put("files", files!!)
        result.put("photo", photo!!)
        return result
    }

}
