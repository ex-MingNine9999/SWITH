## Open Source
출처 : https://github.com/trishume/eyeLike

## eyeLike
An OpenCV based webcam gaze tracker based on a simple image gradient-based eye center algorithm by Fabian Timm.

## DISCLAIMER
**This does not track gaze yet.** It is basically just a developer reference implementation of Fabian Timm's algorithm that shows some debugging windows with points on your pupils.

If you want cheap gaze tracking and don't mind hardware check out [The Eye Tribe](https://theeyetribe.com/).
If you want webcam-based eye tracking contact [Xlabs](http://xlabsgaze.com/) or use their chrome plugin and SDK.
If you're looking for open source your only real bet is [Pupil](http://pupil-labs.com/) but that requires an expensive hardware headset.

## Status
The eye center tracking works well but I don't have a reference point like eye corner yet so it can't actually track
where the user is looking.

If anyone with more experience than me has ideas on how to effectively track a reference point or head pose
so that the gaze point on the screen can be calculated contact me.
