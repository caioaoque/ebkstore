package br.mackenzie.pos.works.persistenceandclientserver.bean.product;

import br.mackenzie.pos.works.persistenceandclientserver.bean.management.UserSessionMBean;
import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import javax.ejb.EJB;

import br.mackenzie.pos.works.persistenceandclientserver.service.OrderService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download/*")
public class DownloadServlet extends HttpServlet {

    @EJB
    private OrderService service;
    @Inject
    private UserSessionMBean userSessionBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Long ebookId = Long.parseLong(request.getPathInfo().substring(1));
        User user = userSessionBean.getService().getUser();
        final byte[] content = this.service.findOrderEbookByEbookIdAndUser(ebookId, user).getContent();
        try (ServletOutputStream out = response.getOutputStream()) {
            response.setHeader("Content-Length", "" + content.length);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + ebookId + "\"");
            out.write(content);
        }
    }

}
