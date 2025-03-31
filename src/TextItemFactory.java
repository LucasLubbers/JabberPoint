public class TextItemFactory extends SlideItemFactory
{
    @Override
    public SlideItem createSlideItem(int level, String message)
    {
        return new TextItem(level, message);
    }
}
