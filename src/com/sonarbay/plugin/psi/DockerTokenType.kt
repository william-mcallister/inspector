package com.sonarbay.plugin.psi

import com.intellij.psi.tree.IElementType
import com.sonarbay.plugin.DockerLanguage

/**
 * Created by sh4d0wz on 8/23/17.
 */
class DockerTokenType (debugName: String) : IElementType(debugName, DockerLanguage.INSTANCE) {

    override fun toString(): String {
        return "DockerTokenType" + super.toString()
    }
}