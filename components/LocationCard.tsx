
import React, { useState } from 'react';
import { NolaLocation } from '../types';
import { getLocationProphecy } from '../services/gemini';
import { Eye, Share2 } from 'lucide-react';
import { PixelPinIcon } from './CustomIcons';

interface LocationCardProps {
  location: NolaLocation;
  revealed?: boolean;
}

export const LocationCard: React.FC<LocationCardProps> = ({ location, revealed = true }) => {
  const [prophecy, setProphecy] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const fetchProphecy = async () => {
    if (prophecy) return;
    setLoading(true);
    const p = await getLocationProphecy(location.name, location.arcana);
    setProphecy(p);
    setLoading(false);
  };

  const [toastMessage, setToastMessage] = useState<string | null>(null);

  const handleShare = async (e: React.MouseEvent) => {
    e.stopPropagation();
    const shareData = {
      title: location.name,
      text: `🔮 Neon Occult NOLA: ${location.name} (${location.arcana})\n${location.vibe}\n${location.address}`,
      url: window.location.origin + window.location.pathname
    };

    if (navigator.share) {
      try {
        await navigator.share(shareData);
      } catch (err) {
        console.debug('Share cancelled or failed', err);
      }
    } else {
      // Fallback: Copy to clipboard
      try {
        await navigator.clipboard.writeText(`${shareData.text}`);
        setToastMessage('Prophecy details copied to clipboard.');
        setTimeout(() => setToastMessage(null), 3000);
      } catch (err) {
        console.error('Clipboard failed', err);
      }
    }
  };

  return (
    <div className={`relative w-full aspect-[2/3] max-w-[320px] mx-auto group perspective-1000 ${revealed ? '' : 'cursor-pointer'}`}>
      {toastMessage && (
        <div className="absolute -top-12 left-1/2 transform -translate-x-1/2 bg-[var(--accent-p)] text-white text-xs font-mono py-2 px-4 shadow-lg z-50 whitespace-nowrap border border-[var(--accent-c)] animate-in fade-in slide-in-from-bottom-2">
          {toastMessage}
        </div>
      )}
      <div className={`relative w-full h-full duration-700 transition-all transform-style-3d ${revealed ? '' : 'hover:scale-105'}`}>

        {/* Card Body - Glassmorphism */}
        <div className="absolute inset-0 bg-[var(--card-bg)] border border-white/10 hover:border-[var(--accent-c)] overflow-hidden flex flex-col p-4 shadow-2xl transition-colors duration-400 backdrop-blur-md">

          {/* Pixelated Image Header */}
          <div className="h-2/5 w-full relative mb-4 overflow-hidden border border-white/10 bg-black">
            <img
              src={location.image}
              alt={location.name}
              className="w-full h-full object-cover grayscale brightness-90 contrast-125 group-hover:grayscale-0 group-hover:brightness-110 transition-all duration-700 pixel-image"
            />
            <div className="absolute bottom-0 left-0 bg-[var(--accent-p)] text-white text-[12px] px-3 py-1 font-glitch uppercase tracking-widest shadow-lg">
              {location.arcana}
            </div>
          </div>

          <div className="flex-grow flex flex-col justify-between">
            <div>
              <h3 className="font-glitch text-3xl leading-none mb-2 text-white group-hover:text-[var(--accent-c)] transition-colors uppercase">{location.name}</h3>
              <p className="text-[12px] font-mono text-[var(--accent-c)] mb-3 font-bold tracking-tight">&gt;&gt; {location.vibe}</p>
              <p className="text-[13px] text-white/80 leading-relaxed font-mono">{location.description}</p>
            </div>

            <div className="mt-4 pt-4 border-t border-dashed border-white/20">
              {prophecy ? (
                <p className="text-[12px] font-mono text-[var(--accent-p)] animate-pulse italic">
                  &gt; {prophecy}
                </p>
              ) : (
                <button
                  onClick={fetchProphecy}
                  disabled={loading}
                  className="w-full flex items-center justify-center gap-2 text-[11px] font-mono py-3 bg-black/50 border border-white/20 hover:bg-[var(--accent-c)] hover:text-black hover:font-bold text-white/70 transition-all uppercase"
                >
                  {loading ? 'CHASING GHOSTS...' : <><Eye size={16} className="pixel-icon" strokeWidth={2}/> CONSULT VEIL</>}
                </button>
              )}
            </div>
          </div>

          {/* Action Buttons: Share & Maps */}
          <div className="absolute top-3 right-3 flex gap-2 z-20">
             <button
                onClick={handleShare}
                className="p-2 bg-black/70 backdrop-blur-md text-[var(--accent-c)] hover:text-white transition-colors border border-[var(--accent-c)]/50 hover:bg-[var(--accent-c)] rounded-none group/share"
                title="Share Destiny"
            >
                <Share2 size={20} className="pixel-icon group-hover/share:scale-110 transition-transform" />
            </button>

            <button
                onClick={(e) => {
                    e.stopPropagation();
                    window.open(`https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(location.name + ' ' + location.address + ' New Orleans')}`, '_blank', 'noopener,noreferrer');
                }}
                className="p-2 bg-black/70 backdrop-blur-md text-[var(--accent-c)] hover:text-white transition-colors border border-[var(--accent-c)]/50 hover:bg-[var(--accent-c)] rounded-none group/pin"
                title="Open Google Maps"
            >
                <PixelPinIcon size={20} className="pixel-icon group-hover/pin:scale-110 transition-transform" />
            </button>
          </div>

        </div>
      </div>
    </div>
  );
};
