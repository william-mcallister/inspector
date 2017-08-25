package com.sonarbay.plugin

import com.intellij.openapi.fileTypes.LanguageFileType

import javax.swing.Icon

/**
 * Created by sh4d0wz on 8/23/17.
 */
class DockerFiletype : LanguageFileType(DockerLanguage.INSTANCE) {
    companion object {
        val INSTANCE = DockerFiletype()
    }

    override fun getName () : String {
        return "SonarBay Dockerfile scanner"
    }

    // return back no extension, docker files do not have an extension
    override fun getDefaultExtension(): String {
        return ""
    }

    override fun getDescription(): String {
        return "Docker files for setting up docker containers"
    }

    override fun getIcon(): Icon? {
        return DockerIcon.Icon
    }
}