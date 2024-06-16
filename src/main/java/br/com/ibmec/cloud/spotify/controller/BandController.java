package br.com.ibmec.cloud.spotify.controller;

import br.com.ibmec.cloud.spotify.controller.request.BandRequest;
import br.com.ibmec.cloud.spotify.controller.request.MusicRequest;
import br.com.ibmec.cloud.spotify.models.Band;
import br.com.ibmec.cloud.spotify.models.Music;
import br.com.ibmec.cloud.spotify.repository.BandRepository;
import br.com.ibmec.cloud.spotify.repository.MusicRepository;
import br.com.ibmec.cloud.spotify.service.AzureSearchIndex;
import br.com.ibmec.cloud.spotify.service.AzureSearchService;
import br.com.ibmec.cloud.spotify.service.AzureStorageAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private AzureStorageAccountService accountService;

    @Autowired
    private AzureSearchService searchService;

    @PostMapping
    public ResponseEntity<Band> create(@Valid @RequestBody BandRequest request) throws Exception {

        Band band = new Band();
        band.setName(request.getName());

        this.bandRepository.save(band);

        for (MusicRequest item : request.getMusics()) {
            Music music = new Music();
            music.setId(UUID.randomUUID());
            music.setName(item.getName());
            music.setDescription(item.getDescription());

            String imageUrl = this.accountService.uploadFileToAzure(item.getImageBase64());
            music.setImage(imageUrl);

            music.setBand(band);

            band.getMusics().add(music);

            this.musicRepository.save(music);

            this.searchService.index(music.getId(), band.getName(), music.getName());
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
    public ResponseEntity<Band> associateMusic(@PathVariable("id") UUID id, @Valid @RequestBody MusicRequest request) throws Exception {
        Optional<Band> optionalBand = this.bandRepository.findById(id);

        if (optionalBand.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Band band = optionalBand.get();

        Music music = new Music();
        music.setName(request.getName());
        music.setDescription(request.getDescription());
        music.setBand(band);

        String imageUrl = this.accountService.uploadFileToAzure(request.getImageBase64());
        music.setImage(imageUrl);

        band.getMusics().add(music);

        this.musicRepository.save(music);

        this.searchService.index(music.getId(), band.getName(), music.getName());

        return new ResponseEntity<>(band, HttpStatus.CREATED);
    }

    @GetMapping("{id}/music")
    public ResponseEntity<List<Music>> getMusics(@PathVariable("id") UUID id) {
        return this.bandRepository.findById(id).map(item -> {
            return new ResponseEntity<>(item.getMusics(), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<AzureSearchIndex>> autocomplete(@RequestParam String search) {
        return new ResponseEntity<>(this.searchService.suggester(search), HttpStatus.OK);
    }
}
