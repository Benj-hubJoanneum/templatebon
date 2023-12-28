package com.ssi.schaefer.lunchbon.acceslogic.irepository

import com.ssi.schaefer.lunchbon.service.api.model.Lunchbon
import org.springframework.stereotype.Repository

@Repository
interface ILunchbonRepository : IGenericRepository<Lunchbon> {
}