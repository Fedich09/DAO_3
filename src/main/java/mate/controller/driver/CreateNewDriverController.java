package mate.controller.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Driver;
import mate.service.DriverService;

public class CreateNewDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private DriverService driverService = (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/driver/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String driverName = req.getParameter("driver_name");
        String licenseNumber = req.getParameter("license_number");
        String driverLogin = req.getParameter("driver_login");
        String driverPassword = req.getParameter("driver_password");
        String repeatDriverPassword = req.getParameter("repeat_driver_password");
        if (driverPassword.equals(repeatDriverPassword)) {
            Driver driver = new Driver(driverName, licenseNumber);
            driver.setLogin(driverLogin);
            driver.setPassword(driverPassword);
            driverService.create(driver);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Your password and repeat password aren't the same.");
            req.getRequestDispatcher("/WEB-INF/view/driver/create.jsp").forward(req, resp);
        }
    }
}
