package com.ssi.schaefer.lunchbon.service.api.controller

import com.ssi.schaefer.lunchbon.service.LunchbonService
import com.ssi.schaefer.lunchbon.service.api.model.Lunchbon
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LunchbonController @Autowired constructor(
    private val lunchbonService: LunchbonService
){

    @GetMapping("/lunchbon")
    fun getLunchbon(@RequestParam id: String): Lunchbon? {
        val lunchbon = lunchbonService.getLunchbonById(id)
        lunchbon?.formatTimeStamp("Europe/Vienna")
        return lunchbon
    }

    @PostMapping("/addLunchbon")
    fun addLunchbon(@RequestBody lunchbon: Lunchbon): ResponseEntity<Any> {
        lunchbonService.createLunchbon(lunchbon)
        return ResponseEntity.ok().body(lunchbon)
    }

    @GetMapping("/lunchbons")
    fun getLunchbons(): List<Lunchbon?> {
        val lunchbons = lunchbonService.getAllLunchbons()
        lunchbons.forEach { it?.formatTimeStamp("Europe/Vienna") }
        return lunchbons
    }

    @DeleteMapping("/lunchbon/delete")
    fun delteLunchbon(@RequestParam id: String): ResponseEntity<Any> {
        val success = lunchbonService.deleteLunchbon(id)
        return if (success) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}