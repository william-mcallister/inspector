package com.sonarbay.plugin

/**
 * Created by sh4d0wz on 8/23/17.
 */
class DockerLanguage private constructor() : com.intellij.lang.Language("Docker") {
    companion object {

        val INSTANCE = com.sonarbay.plugin.DockerLanguage()
    }
}