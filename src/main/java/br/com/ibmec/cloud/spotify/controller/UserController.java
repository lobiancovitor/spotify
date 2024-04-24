package br.com.ibmec.cloud.spotify.controller;

import br.com.ibmec.cloud.spotify.controller.request.LoginRequest;
import br.com.ibmec.cloud.spotify.controller.request.PlaylistRequest;
import br.com.ibmec.cloud.spotify.models.Band;
import br.com.ibmec.cloud.spotify.models.Music;
import br.com.ibmec.cloud.spotify.models.Playlist;
import br.com.ibmec.cloud.spotify.models.User;
import br.com.ibmec.cloud.spotify.repository.MusicRepository;
import br.com.ibmec.cloud.spotify.repository.PlaylistRepository;
import br.com.ibmec.cloud.spotify.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicRepository musicRepository;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {

        if (!this.userRepository.findUserByEmail(user.getEmail()).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Playlist playlist = new Playlist();
        playlist.setId(UUID.randomUUID());
        String DEFAULT_PLAYLIST = "Musicas favoritas";
        playlist.setName(DEFAULT_PLAYLIST);
        playlist.setUser(user);

        user.getPlaylist().add(playlist);

        this.userRepository.save(user);
        this.playlistRepository.save(playlist);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> get(@PathVariable("id") UUID id) {
        return this.userRepository.findById(id).map(user -> {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody LoginRequest request) {
        Optional<User> optionalUser = this.userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());

        return optionalUser.map(user -> {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PostMapping("{id}/like/{musicId}")
    public ResponseEntity like(@PathVariable("id") UUID id, @PathVariable("musicId") UUID musicId) {

        Optional<User> optionalUser = this.userRepository.findById(id);
        Optional<Music> optionalMusic = this.musicRepository.findById(musicId);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (optionalMusic.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();
        Music music = optionalMusic.get();

        user.getPlaylist().get(0).getMusics().add(music);
        playlistRepository.save(user.getPlaylist().get(0));

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("{id}/dislike/{musicId}")
    public ResponseEntity dislike(@PathVariable("id") UUID id, @PathVariable("musicId") UUID musicId) {

        Optional<User> optionalUser = this.userRepository.findById(id);
        Optional<Music> optionalMusic = this.musicRepository.findById(musicId);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (optionalMusic.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();
        Music music = optionalMusic.get();

        for(Music item : user.getPlaylist().get(0).getMusics()) {
            if (item.getId() == music.getId()) {
                user.getPlaylist().get(0).getMusics().remove(music);
                break;
            }
        }

        playlistRepository.save(user.getPlaylist().get(0));

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("{id}/create-playlist")
    public ResponseEntity createPlaylist(@PathVariable("id") UUID id, @Valid @RequestBody PlaylistRequest request) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();

        Playlist playlist = new Playlist();
        playlist.setUser(user);
        playlist.setName(request.getName());

        playlistRepository.save(playlist);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("{id}/playlist/{playlistId}/add/{musicId}")
    public ResponseEntity addMusicPlaylist(@PathVariable("id") UUID id, @PathVariable("playlistId") UUID playlistId, @PathVariable("musicId") UUID musicId) {

        Optional<User> optionalUser = this.userRepository.findById(id);
        Optional<Music> optionalMusic = this.musicRepository.findById(id);
        Optional<Playlist> optionalPlaylist = this.playlistRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (optionalMusic.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (optionalPlaylist.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Playlist playlist = optionalPlaylist.get();
        Music music = optionalMusic.get();

        playlist.getMusics().add(music);

        playlistRepository.save(playlist);

        return new ResponseEntity(optionalUser.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}/playlist/{playlistId}/remove/{musicId}")
    public ResponseEntity removeMusicPlaylist(@PathVariable("id") UUID id, @PathVariable("playlistId") UUID playlistId, @PathVariable("musicId") UUID musicId) {

        Optional<User> optionalUser = this.userRepository.findById(id);
        Optional<Music> optionalMusic = this.musicRepository.findById(id);
        Optional<Playlist> optionalPlaylist = this.playlistRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (optionalMusic.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (optionalPlaylist.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Playlist playlist = optionalPlaylist.get();
        Music music = optionalMusic.get();

        for(Music item : playlist.getMusics()) {
            if (item.getId() == music.getId()) {
                playlist.getMusics().remove(music);
                break;
            }
        }

        playlistRepository.save(playlist);

        return new ResponseEntity(optionalUser.get(), HttpStatus.OK);
    }
}
