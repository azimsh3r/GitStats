package com.gitstats.project.controllers

import com.gitstats.project.models.SearchEntity
import com.gitstats.project.services.StatsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/stats")
class StatsController (@Autowired val statsService: StatsService){

    @GetMapping
    fun getMain(model : Model) : String {
        model.addAttribute("searchEntity", SearchEntity())
        return "stats/index"
    }

    @PostMapping
    fun getRepo(@ModelAttribute searchEntity: SearchEntity, model: Model) : String {
        val repositories = statsService.getRepositories(searchEntity.author+"/"+searchEntity.repositoryName)
        model.addAttribute("repositories", repositories)
        return "stats/index"
    }

    @PostMapping("/repoAnalytics")
    fun getAnalytics(@RequestParam("repositoryFullName") fullName: String, model: Model) : String {
        model.addAttribute("developerPairs", statsService.getContributors(fullName))
        model.addAttribute("repository", statsService.getRepositories(fullName)[0])
        return "stats/repoInfo"
    }
}
