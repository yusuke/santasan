package santa;

import ch.deathmar.Store;
import twitter4j.DirectMessage;
import twitter4j.internal.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yusuke
 * Date: 2012/12/08
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class SantaServlet extends HttpServlet {
    Logger logger = Logger.getLogger(SantaServlet.class);
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        DirectMessage message = (DirectMessage)Store.get(req.getParameter("id"));
        req.setAttribute("message", message);
        req.getRequestDispatcher("/santa.jsp").forward(req, res);
    }

    SantaThread santaThread;
    ServletContext context;
    @Override
    public void init(ServletConfig config) {
        context = config.getServletContext();
//        santaThread = new SantaThread("http://localhost:8080"+getServletContext().getContextPath());
        santaThread = new SantaThread("http://localhost:8080");
    }

    @Override
    public void destroy() {
        santaThread.shutdown();
    }


}
