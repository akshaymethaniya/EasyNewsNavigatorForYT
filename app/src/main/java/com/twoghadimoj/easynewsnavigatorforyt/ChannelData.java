package com.twoghadimoj.easynewsnavigatorforyt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public  class ChannelData {
    private Map<Language, ArrayList<Channel>> languageChannelMapping ;

    public ChannelData() {
        this.languageChannelMapping = new HashMap<>();
        initializeMap();
    }

    private void initializeMap() {
        //Create Channels For Gujarati Language
        ArrayList<Channel> gujaratiChannels = new ArrayList<>();
        gujaratiChannels.add(new Channel(R.id.tv9Channel,Language.GUJARATI,"UCeJWZgSMlzqYEDytDnvzHnw"));
        gujaratiChannels.add(new Channel(R.id.abpAsmitaChannel,Language.GUJARATI,"UC3C6_1ETXfE807LltDbKYxg"));
        gujaratiChannels.add(new Channel(R.id.sandeshChannel,Language.GUJARATI,"UCiAH2s_M6nPfGZk-PpfyPkg"));
        gujaratiChannels.add(new Channel(R.id.news18Channel,Language.GUJARATI,"UCqfX_f4rBbPhq_KESE4saCA"));
        gujaratiChannels.add(new Channel(R.id.gstvChannel,Language.GUJARATI,"UCR6arqcGnmild76MuXQjezA"));
        gujaratiChannels.add(new Channel(R.id.zee24hourChannel,Language.GUJARATI,"UCkNL_TQio--h85-14lUVY3A"));
        gujaratiChannels.add(new Channel(R.id.ddNewsGujaratiChannel,Language.GUJARATI,"UCpOyT470_JSYECdJV4p2cUw"));
        gujaratiChannels.add(new Channel(R.id.mantvyaChannel,Language.GUJARATI,"UCTvTJrGXzde0ByxjtaQrxsg"));

        //Create Channels For Gujarati Language
        ArrayList<Channel> hindiChannels = new ArrayList<>();
        hindiChannels.add(new Channel(R.id.aajTakChannel,Language.HINDI,"UCt4t-jeY85JegMlZ-E5UWtA"));
        hindiChannels.add(new Channel(R.id.abpNewsChannel,Language.HINDI,"UCRWFSbif-RFENbBrSiez1DA"));
        hindiChannels.add(new Channel(R.id.republicBharatChannel,Language.HINDI,"UC7wXt18f2iA3EDXeqAVuKng"));
        hindiChannels.add(new Channel(R.id.ndtvChannel,Language.HINDI,"UC9CYT9gSNLevX5ey2_6CK0Q"));
        hindiChannels.add(new Channel(R.id.indiaTvChannel,Language.HINDI,"UCttspZesZIDEwwpVIgoZtWQ"));
        hindiChannels.add(new Channel(R.id.zeeNewsChannel,Language.HINDI,"UCIvaYmXn910QMdemBG3v1pQ"));

        languageChannelMapping.put(Language.GUJARATI,gujaratiChannels);
        languageChannelMapping.put(Language.HINDI,hindiChannels);
    }

    public Map<Language, ArrayList<Channel>> getLanguageChannelMapping() {
        return languageChannelMapping;
    }
}
