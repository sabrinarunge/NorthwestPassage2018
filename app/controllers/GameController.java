package controllers;

import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class GameController extends Controller
{
    private final String remainingCrewSessionKey = "remainingcrew";
    private FormFactory formFactory;

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
        String name = form.get("playername");
        Logger.info("form playername: " + name);
        if(name != null)
        {
            session().put("playername", name);
        }

        String nameFromSession = session().get("playername");
        Logger.info("session playername: " + nameFromSession);

        int numberOfCrew = 10;
        session().put(remainingCrewSessionKey, "" + numberOfCrew);

        return ok(views.html.start.render());
    }

    public Result postEastFromEngland()
    {
        String name = session().get("playername");
        int numberOfCrew = Integer.parseInt(session().get(remainingCrewSessionKey));
        numberOfCrew--;
        session().put(remainingCrewSessionKey, "" + numberOfCrew);

        boolean failure = false;

        if (numberOfCrew < 5)
        {
            failure = true;
            return ok(views.html.oakisland.render(name));
        }

        else
        {
            failure = false;
            return ok(views.html.eastfromengland.render("" + numberOfCrew));
        }
    }

    public Result postNorthFromEngland()
    {
        String name = session().get("playername");
        return ok(views.html.northfromengland.render(name));
    }

    public Result postWestFromEngland()
    {
        String name = session().get("playername");
        int numberOfCrew = Integer.parseInt(session().get(remainingCrewSessionKey));
        numberOfCrew--;
        session().put(remainingCrewSessionKey, "" + numberOfCrew);

        boolean failure = false;

        if (numberOfCrew < 5)
        {
            failure = true;
            return ok(views.html.oakisland.render(name));
        }

        else
        {
            failure = false;
            return ok(views.html.westfromengland.render("" + numberOfCrew));
        }
    }

    public Result postKeepGoingNorth()
    {
        return ok(views.html.keepgoingnorth.render());
    }

    public Result postEastEnd()
    {
        String name = session().get("playername");
        return ok(views.html.eastend.render(name));
    }

    public Result postWestEnd()
    {
        String name = session().get("playername");
        return ok(views.html.westend.render(name));
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render());
    }

    public Result postOakIsland()
    {
        String name = session().get("playername");
        return ok(views.html.oakisland.render(name));
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
