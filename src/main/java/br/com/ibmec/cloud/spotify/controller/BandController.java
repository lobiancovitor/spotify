package br.com.ibmec.cloud.spotify.controller;

import br.com.ibmec.cloud.spotify.controller.request.BandRequest;
import br.com.ibmec.cloud.spotify.controller.request.MusicRequest;
import br.com.ibmec.cloud.spotify.models.Band;
import br.com.ibmec.cloud.spotify.models.Music;
import br.com.ibmec.cloud.spotify.repository.BandRepository;
import br.com.ibmec.cloud.spotify.repository.MusicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bands")
public class BandController {
    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private MusicRepository musicRepository;

    @PostMapping
    public ResponseEntity<Band> create(@Valid @RequestBody BandRequest request) {

        Band band = new Band();
        band.setName(request.getName());
        band.setImage(request.getImage());

        this.bandRepository.save(band);

        for (MusicRequest item : request.getMusics()) {
            Music music = new Music();
            music.setId(UUID.randomUUID());
            music.setName(item.getName());
            music.setDuration(item.getDuration());
            music.setBand(band);


            band.getMusics().add(music);

            this.musicRepository.save(music);
        }

        return new ResponseEntity<>(band, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Band>> getAll() {
        List<Band> bands = this.bandRepository.findAll();
        if (bands.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bands, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Band> get(@PathVariable("id") UUID id) {
        return this.bandRepository.findById(id).map(item -> {
            return new ResponseEntity<>(item, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/music")
    public ResponseEntity<Band> associateMusic(@PathVariable("id") UUID id, @Valid @RequestBody MusicRequest request) {
        Optional<Band> optionalBand = this.bandRepository.findById(id);

        if (optionalBand.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Band band = optionalBand.get();

        Music music = new Music();
        music.setName(request.getName());
        music.setDuration(request.getDuration());
        music.setBand(band);
        band.getMusics().add(music);

        this.musicRepository.save(music);

        return new ResponseEntity<>(band, HttpStatus.CREATED);
    }

    @GetMapping("{id}/music")
    public ResponseEntity<List<Music>> getMusics(@PathVariable("id") UUID id) {
        return this.bandRepository.findById(id).map(item -> {
            return new ResponseEntity<>(item.getMusics(), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
