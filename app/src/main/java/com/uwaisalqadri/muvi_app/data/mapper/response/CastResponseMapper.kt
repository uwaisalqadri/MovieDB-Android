package com.uwaisalqadri.muvi_app.data.mapper.response

import com.uwaisalqadri.muvi_app.data.mapper.BaseMapper
import com.uwaisalqadri.muvi_app.data.source.remote.response.CastItem
import com.uwaisalqadri.muvi_app.domain.model.Cast

/**
 * Created by Uwais Alqadri on April 06, 2021
 */
class CastResponseMapper : BaseMapper<CastItem, Cast> {
    override fun mapToDomain(model: CastItem): Cast {
        return Cast(
            model.adult,
            model.cast_id,
            model.character,
            model.credit_id,
            model.gender,
            model.id,
            model.known_for_department,
            model.name,
            model.order,
            model.original_name,
            model.popularity,
            model.profile_path
        )
    }

    override fun mapToModel(domain: Cast): CastItem {
        return CastItem(
            domain.adult,
            domain.cast_id,
            domain.character,
            domain.credit_id,
            domain.gender,
            domain.id,
            domain.known_for_department,
            domain.name,
            domain.order,
            domain.original_name,
            domain.popularity,
            domain.profile_path
        )
    }
}













