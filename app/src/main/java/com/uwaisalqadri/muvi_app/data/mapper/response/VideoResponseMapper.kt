package com.uwaisalqadri.muvi_app.data.mapper.response

import com.uwaisalqadri.muvi_app.data.mapper.BaseMapper
import com.uwaisalqadri.muvi_app.data.source.remote.response.VideoItem
import com.uwaisalqadri.muvi_app.domain.model.Video

/**
 * Created by Uwais Alqadri on April 06, 2021
 */
class VideoResponseMapper : BaseMapper<VideoItem, Video> {
    override fun mapToDomain(model: VideoItem): Video {
        return Video(
            model.id,
            model.key,
            model.name,
            model.site,
            model.size,
            model.type
        )
    }

    override fun mapToModel(domain: Video): VideoItem {
        return VideoItem(
            domain.id,
            domain.key,
            domain.name,
            domain.site,
            domain.size,
            domain.type
        )
    }
}