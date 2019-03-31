import java.util.Comparator;

class MileageComparator implements Comparator<Destination>
{
    @Override
    public int compare(Destination d1, Destination d2) 
    {
        if(d1.getNormalMiles() > d2.getNormalMiles()) 
        {
            return -1;
        }
        else if(d1.getNormalMiles() < d2.getNormalMiles()) 
        {
            return 1;
        }
        else return 0;
    }
}