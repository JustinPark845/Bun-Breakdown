package Components;

import Functions.FileManipulator;
import Functions.Manipulator;
import Functions.StringManipulator;
import Functions.TimeManipulator;

interface ManipulatorMaker {
    public Manipulator makeManipulator();
}

class FileManipulatorMaker implements ManipulatorMaker
{
    public Manipulator makeManipulator()
    {
        return new FileManipulator();
    }
}
class StringManipulatorMaker implements ManipulatorMaker
{
    public Manipulator makeManipulator()
    {
        return new StringManipulator();
    }
}
class TimeManipulatorMaker implements ManipulatorMaker
{
    public Manipulator makeManipulator()
    {
        return new TimeManipulator();
    }
}