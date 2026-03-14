package util;

import model.PixelData;

@FunctionalInterface
public interface PixelTransformer {
    PixelData apply(PixelData data);

    default PixelTransformer combine(PixelTransformer next){
        return (data) -> next.apply(this.apply(data));
    }
}
