package ru.kpfu.itis.gadelev;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    SearchHelper searchHelper = new SearchHelper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("search.ftlh").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bus = request.getParameter("bus");
        //ендпоинты не работали всю кр вслепую парсинг писал,по факту должно работать.
        String JSON = searchHelper.get("http://data.kzn.ru:8082/api/v0/dynamic_datasets/bus/"+bus+".json");
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(JSON, Map.class);
        String normalString = new Gson().toJson(map.get("data"));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(normalString);
    }
}