package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class GameController extends Controller
{
    private FormFactory formFactory;
    private String name;

    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }

    public Result getWelcome()
    {
        return ok(views.html.welcome.render());
    }

    public Result postStart()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        name = form.get("playername");
        session().put("playername", name);
        return ok(views.html.start.render());
    }

    public Result postEastFromEngland()
    {
        return ok(views.html.eastfromengland.render());
    }

    public Result postNorthFromEngland()
    {
        return ok(views.html.northfromengland.render(name));
    }

    public Result postWestFromEngland()
    {
        return ok(views.html.westfromengland.render());
    }

    public Result postKeepGoingNorth(){return ok(views.html.keepgoingnorth.render());}

    public Result postEastEnd()
    {
        return ok(views.html.eastend.render(name));
    }

    public Result postWestEnd()
    {
        return ok(views.html.westend.render(name));
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render());
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
