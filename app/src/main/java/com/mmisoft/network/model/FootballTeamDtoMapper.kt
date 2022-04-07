package com.mmisoft.network.model

import com.mmisoft.domain.model.FootballTeam
import com.mmisoft.domain.util.DomainMapper

class FootballTeamDtoMapper: DomainMapper<FootballTeamDto, FootballTeam> {

    override fun mapToDomainModel(model: FootballTeamDto): FootballTeam {
        return FootballTeam(
            id = model.id,
            name = model.name,
            country = model.country,
            value = model.value,
            image = model.image,
            european_titles = model.european_titles,
            stadium = model.stadium,
            location = model.location,
        )
    }

    override fun mapFromDomainModel(domainModel: FootballTeam): FootballTeamDto {
        return FootballTeamDto(
            id = domainModel.id,
            name = domainModel.name,
            country = domainModel.country,
            value = domainModel.value,
            image = domainModel.image,
            european_titles = domainModel.european_titles,
            stadium = domainModel.stadium,
            location = domainModel.location,
        )
    }

    fun toDomainList(initial: List<FootballTeamDto>): List<FootballTeam>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<FootballTeam>): List<FootballTeamDto>{
        return initial.map { mapFromDomainModel(it) }
    }
}