package com.swj.quicksearchplace.model

data class KakaoSearchPlaceResponse(
    val meta:PlaceMeta,
    val documents:MutableList<Place>
)

data class PlaceMeta(
    val total_count:Int,
    val pageable_count:Int,
    val is_end:Boolean
)

data class Place(
    val id:String,
    val place_name:String,
    val category_name:String,
    val category_group_code:String,
    val category_group_name:String,
    val phone:String,
    val address_name:String,
    val road_address_name:String,
    val x:String,   // longitude (경도)
    val y:String,   // latitude  (위도)
    val place_url:String,
    val distance:String     // 중심 좌표까지의 거지.. 단, 요청 파라미터로 x,y 좌표를 준 경우만 존재. 단위 meter
)


