package com.gitstats.project.services

import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class StatsService {

    fun getRepositories(query: String) : List<GHRepository>{
        val github = GitHub.connectUsingOAuth(
            "https://api.github.com",
            AuthService.token
        )
        return github.searchRepositories().q(query).list().toList()
    }

    fun getContributors(fullName:String) : MutableMap<String, String> {
        val mostFreqMap = mutableMapOf<String, String>()

        try {
            val github = GitHub.connectUsingOAuth(
                "https://api.github.com",
                AuthService.token
            )
            val repository = github.getRepository(fullName)
            val commits = repository.listCommits()

            val contributorMap = mutableMapOf<String, MutableSet<String>>()

            // Marks contributors for each file
            for (commit in commits) {
                for (file in commit.listFiles()) {
                    val fileName = file.fileName
                    val authorName = commit.author?.name
                    if (authorName != null) {
                        contributorMap.computeIfAbsent(fileName) { mutableSetOf() }.add(authorName)
                    }
                }
            }

            val contributorPair = mutableMapOf<String, MutableMap<String, Int>>()

            // Marks developers who contributed to same files
            for (entry in contributorMap) {
                val devSet = entry.value
                for (dev1 in devSet) {
                    for (dev2 in devSet) {
                        if (dev1 != dev2) {
                            val pairKey = if (dev1 < dev2) "$dev1-$dev2" else "$dev2-$dev1"
                            // Increment the count for the developer pair
                            contributorPair.getOrPut(dev1) { mutableMapOf() }
                                .merge(dev2, 1, Int::plus)
                        }
                    }
                }
            }

            // Finds people with the highest contribution frequency
            for ((fileName, pairs) in contributorPair) {
                val topPair: String? = pairs.entries.maxByOrNull { it.value }?.key
                mostFreqMap[fileName] = topPair ?: "error"
            }
        } catch (e: IOException) {
            println(e.message)
            println(e.stackTrace)
        }
        return mostFreqMap
    }
}
