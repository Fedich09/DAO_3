package mate.controller.driver;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Driver;
import mate.service.DriverService;

public class DisplayAllDriversController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Driver> listOfDrivers = driverService.getAll();
        req.setAttribute("drivers", listOfDrivers);
        req.getRequestDispatcher("/WEB-INF/view/driver/all.jsp").forward(req, resp);
    }
}
