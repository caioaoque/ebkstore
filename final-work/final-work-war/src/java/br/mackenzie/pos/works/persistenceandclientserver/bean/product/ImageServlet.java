package br.mackenzie.pos.works.persistenceandclientserver.bean.product;

import javax.ejb.EJB;

import br.mackenzie.pos.works.persistenceandclientserver.service.EbookService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    @EJB
    private EbookService service;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Long ebookId = Long.parseLong(request.getPathInfo().substring(1));
        final byte[] cover = this.service.findById(ebookId).getCover();
        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(cover);
        }
    }

}
