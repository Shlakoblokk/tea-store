package edu.festu.ivankuznetsov.springsamplebo94xpri.controller

import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.nio.file.Files


@Controller
@RequestMapping("/assets")
class StaticResourceController {

    @GetMapping("/styles/{fileName:.+}")
    @Throws(IOException::class)
    fun getStyles(@PathVariable fileName: String): ResponseEntity<ByteArray> {
        return getResource("styles", fileName, "text/css")
    }

    @GetMapping("/js/{fileName:.+}")
    @Throws(IOException::class)
    fun getScripts(@PathVariable fileName: String): ResponseEntity<ByteArray> {
        return getResource("js", fileName, "application/javascript")
    }

    private fun getResource(subdirectory: String, fileName: String, contentType: String): ResponseEntity<ByteArray> {
        val resource = ClassPathResource("/assets/$subdirectory/$fileName")

        return if (resource.exists()) {
            val data = Files.readAllBytes(resource.file.toPath())
            ResponseEntity.ok().contentType(MediaType.valueOf(contentType)).body(data)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}