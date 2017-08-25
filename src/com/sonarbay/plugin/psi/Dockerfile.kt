package com.sonarbay.plugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.sonarbay.plugin.DockerFiletype
import com.sonarbay.plugin.DockerIcon
import com.sonarbay.plugin.DockerLanguage
import javax.swing.Icon

/**
 * Created by sh4d0wz on 8/23/17.
 */
class Dockerfile (viewProvider: FileViewProvider) : PsiFileBase(viewProvider, DockerLanguage.INSTANCE){

    override fun getFileType(): FileType {
        return DockerFiletype.INSTANCE
    }

    override fun toString(): String {
        return "Dockerfile"
    }

    override fun getIcon(flags: Int): Icon? {
        return super.getIcon(flags)
    }
}