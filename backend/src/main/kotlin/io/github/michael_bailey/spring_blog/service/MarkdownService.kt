@file:Project("markdown")

package io.github.michael_bailey.spring_blog.service

import net.michael_bailey.metadata.Project
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.stereotype.Service

@Service
class MarkdownService {
    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build()

    fun render(markdown: String): String {
        val document = parser.parse(markdown)
        return renderer.render(document)
    }
}
