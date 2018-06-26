package codehive.foodrecept.models.view;

public class Test {
    private int id;
    private String name, rname, image, nextpage;

    public Test() {
    }

    public Test(int id, String name, String rname, String image,
                String nextpage) {

        this.id = id;
        this.name = name;
        this.rname = rname;
        this.image = image;
        this.nextpage = nextpage;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNextpage() {
        return nextpage;
    }

    public void setNextpage(String nextpage) {
        this.nextpage = nextpage;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
