package com.sonarbay.plugin

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

/**
 * Created by sh4d0wz on 8/24/17.
 */
class DockerHighlightFactory () : SyntaxHighlighterFactory () {

    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
        return DockerHighlighter ();
    }
}