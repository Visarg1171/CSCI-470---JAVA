class Destination
{
    int normalMiles,
            supersaverMiles,
            additionalMiles,
            startMonth,
            endMonth;
    String destinationCity;
    Destination(int normalMiles,int supersaverMiles,int additionalMiles,int startMonth,int endMonth,String destinationCity)
    {
        this.normalMiles = normalMiles;
        this.supersaverMiles = supersaverMiles;
        this.additionalMiles = additionalMiles;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.destinationCity = destinationCity;
    }
    public void setNormalMiles(int normalMiles)
    {
        this.normalMiles = normalMiles;
    }
    public int getNormalMiles()
    {
        return normalMiles;
    }
    public void setSuperSaverMiles(int supersaverMiles)
    {
        this.supersaverMiles = supersaverMiles;
    }
    public int getSuperSaverMiles()
    {
        return supersaverMiles;
    }
    public void setAdditionalMiles(int additionalMiles)
    {
        this.additionalMiles = additionalMiles;
    }
    public int getAdditionalMiles()
    {
        return additionalMiles;
    }
    public void setStartMonth(int startMonth)
    {
        this.startMonth = startMonth;
    }
    public int getStartMonth()
    {
        return startMonth;
    }
    public void setEndMonth(int endMonth)
    {
        this.endMonth = endMonth;
    }
    public int getEndMonth()
    {
        return endMonth;
    }
    public void setDestinationCity(String destinationCity)
    {
        this.destinationCity = destinationCity;
    }
    public String getDestinationCity()
    {
        return destinationCity;
    }

}