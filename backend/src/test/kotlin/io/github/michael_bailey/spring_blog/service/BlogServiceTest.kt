package io.github.michael_bailey.spring_blog.service

import io.github.michael_bailey.spring_blog.model.BlogPostModel
import io.github.michael_bailey.spring_blog.repository.old.BlogRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockitoExtension::class)
class BlogServiceTest {

    @Mock
    lateinit var blogRepository: BlogRepository

    @InjectMocks
    lateinit var blogService: BlogService

    @Test
    fun `getByName returns post if found`() {
        val post = BlogPostModel(name = "test", title = "Test Post", content = "Content")
        `when`(blogRepository.findByName("test")).thenReturn(Optional.of(post))

        val result = blogService.getByName("test")

        assertNotNull(result)
        assertEquals("Test Post", result?.title)
    }

    @Test
    fun `getByName returns null if not found`() {
        `when`(blogRepository.findByName("missing")).thenReturn(Optional.empty())

        val result = blogService.getByName("missing")

        assertNull(result)
    }

    @Test
    fun `getAllBlogPosts returns list of posts`() {
        val posts = listOf(
            BlogPostModel(name = "one", title = "One", content = "Content"),
            BlogPostModel(name = "two", title = "Two", content = "Content")
        )
        `when`(blogRepository.findAll()).thenReturn(posts)

        val result = blogService.getAllBlogPosts()

        assertEquals(2, result.size)
        assertEquals("One", result[0].title)
    }

    @Test
    fun `createPost saves and returns blog post`() {
        val name = "new-post"
        val title = "New Post"
        val content = "Some content"
        val savedPost = BlogPostModel(name = name, title = title, content = content)

        `when`(blogRepository.save(any(BlogPostModel::class.java))).thenReturn(savedPost)

        val result = blogService.createPost(name, title, content)

        assertEquals(name, result.name)
        assertEquals(title, result.title)
        assertEquals(content, result.content)
    }
}