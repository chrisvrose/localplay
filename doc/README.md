# localplay

# W3H

### What (is it for?)

Play pre-loaded videos as a group.

### When (is it useful?)

Useful for poor internet connections. Files can be pre-shared, and loaded separately.

### How (does it do its work?)

1. Login, and create a new session.
2. (For everyone else) Join a session using the ID
3. Join a session using the provided link
4. Set the video file 
   - For the host this sets the session video file (+ subtitles?).
   - For the participant, this requires a validation (if the correct video file is provided)
5. Play.
6. Play and pause as required.
   - If enabled by host, play/pause power is with the participants as well.


### User Flows

### Host

1. Login
2. Create a session. This gives a page to see the user's existing sessions.
3. Get the link for the session.
4. Open the session.
5. Load the video, subtitles. Set the details for the session.
6. Play/Pause.
7. Cleanup session.

### Participant

1. Open link. Store nickname.
2. Open session. 
3. Load the video and any related subtitles.
4. Play/Pause.
5. Exit