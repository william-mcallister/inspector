package com.sonarbay.plugin

import com.intellij.lexer.FlexAdapter
import java.io.Reader

/**
 * Created by sh4d0wz on 8/23/17.
 */
class DockerAdapter : FlexAdapter (DockerLexer(null)) {

}